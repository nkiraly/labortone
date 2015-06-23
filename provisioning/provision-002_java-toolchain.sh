#!/bin/bash

this_machine_ip=$1

echo "LaborTone Provisioning Step 002 - java runtime and toolchain configuration"

ansible=`which ansible-playbook 2>/dev/null`

# abort on error
set -e

# if ansible was not found, error
if [[ -z $ansible ]]; then
  echo "ansible-playbook not found. step 001 should have installed it"
  exit 10
fi

# switch to vagrant shared folder from hosts' project working dir
cd /vagrant

# use ansible to provision this VM with runtime and configuration we need to run the labortone java jar
# inventory string 'localhost,' makes python consider the text a list and ansible will run with this
$ansible \
  -i 'localhost,' \
  -c local \
  provisioning/provision-002_java-toolchain.yml
