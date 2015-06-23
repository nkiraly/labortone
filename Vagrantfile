require 'rbconfig'

is_windows = (RbConfig::CONFIG['host_os'] =~ /mswin|mingw|cygwin/)

MACHINE_IP = '10.0.5.10'
SSH_PORT = 5022
HTTP_PORT = 5080
HTTPS_PORT = 5443

Vagrant.configure('2') do |config|

  config.vm.define 'default' do |labortone|
    labortone.vm.box = 'chef/centos-6.6'

    labortone.vm.network 'forwarded_port', guest: 22, host: SSH_PORT, id: 'ssh'
    labortone.vm.network 'forwarded_port', guest: 80, host: HTTP_PORT
    labortone.vm.network 'forwarded_port', guest: 443, host: HTTPS_PORT

    labortone.vm.network 'private_network', ip: MACHINE_IP

    # mount current working dir as /vagrant in the VM
    labortone.vm.synced_folder '.', '/vagrant', :mount_options => ["dmode=777","fmode=666"]

    labortone.vm.provider 'virtualbox' do |vb|
      vb.gui = true
      vb.memory = 512
      vb.customize ["modifyvm", :id, "--hwvirtex", "on"]
      vb.customize ["modifyvm", :id, "--audio", "none"]
      vb.customize ["modifyvm", :id, "--nictype1", "virtio"]
      vb.customize ["modifyvm", :id, "--nictype2", "virtio"]
    end

    # provision step 001
    # specify a shell provisioner in the labortone project to make sure yum is up to date
    labortone.vm.provision 'shell', path: 'provisioning/provision-001_yum-ansible.sh', args: [MACHINE_IP], privileged: false

    # provision step 002
    if is_windows
      # if vagrant host is windows, use shell to run ansible inside the VM
      labortone.vm.provision 'shell', path: 'provisioning/provision-002_java-toolchain.sh', args: [MACHINE_IP], privileged: false
    else
      # if no os cases, use host ansible to provision runtime
      # specify a vagrant provisioner to make sure runtime and configuration is as expected
      labortone.vm.provision 'ansible' do |ansible|
        ansible.playbook = "provisioning/provision-002_java-toolchain.yml"
        # debug
        # ansible.verbose = 'vvvv'
      end
    end

    # provision step 010
    # specify a shell provisioner to build labortone jar and run it
    labortone.vm.provision 'shell', path: 'provisioning/provision-010_run-labortone.yml', args: [MACHINE_IP], privileged: false
  end

end
