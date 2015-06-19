#!/bin/bash

this_machine_ip=$1

ansible=`which ansible-playbook 2>/dev/null`

# abort on error
set -e

# if ansible was not found, error
if [[ -z $ansible ]]; then
  echo "ansible-playbook not found. step 001 should have installed it"
  exit 10
fi

# switch to vagrant shared folder from host current working dir
cd /vagrant

# use ansible to provision this VM with runtime and configuration we need to run the taskplanner java jar
$ansible \
  # inventory host, makes python consider the text a list and ansible will run with this
  -i 'localhost,' \
  -c local \
  provisioning/provision-002_runtime.yml
