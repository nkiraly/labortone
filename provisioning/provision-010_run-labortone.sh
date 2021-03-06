#!/bin/bash

this_machine_ip=$1

echo "LaborTone Provisioning Step 010 - build labortone maven project"

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

# do a full fresh build of the labortone jar
mvn clean verify

echo "LaborTone Provisioning Step 010 - calling labortone jar with test input files"

# run the labortone application with sample data file resources in the project
# these are the same file resources the junit tests use
java -jar target/labortone-0.1.0-SNAPSHOT-withdepends.jar \
  --taskfile src/test/resources/tasks001.yml \
  --resourcefile src/test/resources/resources001.yml
