#!/bin/bash

this_machine_ip=$1

ansible=`which ansible-playbook 2>/dev/null`

# abort on error
set -e

# if ansible was not found, install it before continuing
if [[ -z $ansible ]]; then
  sudo yum -y update
  sudo yum -y install epel-release
  sudo yum -y install ansible
  hash -r
  ansible=`which ansible-playbook 2>/dev/null`
fi
