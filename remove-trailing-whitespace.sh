#!/bin/bash

git ls-files | xargs sed -i '' -E "s/[[:space:]]*$//"
