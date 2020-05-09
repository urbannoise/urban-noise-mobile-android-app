#!/bin/bash

# This file simplifies the decryption of the different keys needed to compile the project
# Example of use: sh signing-setup.sh [ENCRYPT KEY]

ENCRYPT_KEY=$1

if [[ ! -z "$ENCRYPT_KEY" ]]; then
  # Decrypt Google Services keys
  openssl aes-256-cbc -md sha256 -d -in ../app/src/qa/google-services.aes -out ../app/src/qa/google-services.json -k ${ENCRYPT_KEY}
  openssl aes-256-cbc -md sha256 -d -in ../app/src/prod/google-services.aes -out ../app/src/prod/google-services.json -k ${ENCRYPT_KEY}
else
  echo "ENCRYPT_KEY is empty"
fi
