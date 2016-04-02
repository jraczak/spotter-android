require 'calabash'

if Calabash::Environment.xamarin_test_cloud?
  module Calabash
    module Android
      class ADB
        def self.command(*cmd, **args)
          Logger.debug("ADB Command: #{cmd.join(', ')}")
          Logger.debug("ADB input: #{args[:input]}")

          cmd_s = "#{Environment.adb_path} #{cmd.join(' ')}"

          Logger.debug("Going to execute: #{cmd_s}")
          `#{cmd_s}`
        end

        # @!visibility private
        def shell(shell_cmd, options={})
          command('shell', shell_cmd)
        end
      end
    end
  end

  # Map from device identifier to list of host-stored applications
  $xtc_installed_applications = {}

  def xtc_installed_applications(device_identifier)
    $xtc_installed_applications[device_identifier] ||= []
  end

  def xtc_installed_application(device_identifier, package)
    xtc_installed_applications(device_identifier)
        .find {|app| app.identifier == package}
  end

  def xtc_installed_application_remove!(device_identifier, package)
    xtc_installed_applications(device_identifier)
        .delete_if {|app| app.identifier == package}
  end

  module XTCPatches
    module Android
      module DevicePatches
        # Reason: We cannot run dumpsys power and the screen is always on in XTC
        def screen_on?
          true
        end

        # Reason: pm list packages -f is not allowed
        def installed_apps
          packages = installed_packages

          packages.map{|p| {package: p, path: nil}}
        end

        # Reason: We cannot deploy and run the md5 binary
        def md5_checksum_for_app_package(package)
          unless installed_apps.find{|app| app[:package] == package}
            raise "Application with package '#{app}' not installed"
          end

          app = xtc_installed_application(self.identifier, package)

          unless app
            raise "Application with package '#{app}' not installed as XTC"
          end

          app.md5_checksum
        end

        # Reason: XTC only accepts adb install [...]
        def adb_install_app(application)
          @logger.log "Patch: Installing #{application.path}"

          begin
            result = adb.command("install #{application.path}", timeout: 60)
          rescue ADB::ADBCallError => e
            raise "Failed to install the application on device: '#{e.message}'"
          end

          if result.lines.last.downcase.chomp != 'success'
            raise "Could not install app '#{application.identifier}': #{result.chomp}"
          end

          unless installed_packages.include?(application.identifier)
            raise "App '#{application.identifier}' was not installed"
          end

          xtc_installed_applications(self.identifier) << application
        end

        # Reason: We need to track the uninstalled app
        def adb_uninstall_app(application)
          xtc_installed_application_remove!(self.identifier, application)
          super(application)
        end

        # Reason: Adb shell 'ls' not allowed
        def calabash_server_failure_exists?(application)
          # ULTRA HACK! Just return false
          false
        end

        # Reason: Adb shell 'ls' not allowed
        def calabash_server_finished_exists?(application)
          # ULTRA HACK! Just return true
          true
        end

        # Reason: Adb shell 'cat' not allowed
        def read_calabash_sever_failure(application)
          # ULTRA HACK! Just return ''
          ''
        end

        # Reason: Adb shell 'cat' not allowed
        def read_calabash_sever_finished(application)
          # ULTRA HACK! Just return 'SUCCESSFUL'
          'SUCCESSFUL'
        end

        # Reason: Not allowed to supply extras in adb shell am start
        # And we don't use these anyway
        def clear_calabash_server_report(application)
          true
        end
      end
    end
  end

  module Calabash
    module Android
      class Device < Calabash::Device
        prepend ::XTCPatches::Android::DevicePatches
      end
    end
  end
end