#!/usr/bin/env bash
set -e
set -x
echo "Running tests"
pipenv run behave --tags=@smoke
