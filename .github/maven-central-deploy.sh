#!/bin/bash
#
# This script takes care of deploying tagged versions to maven central and updating the pom.xml
# version with next development version.
#
# Required environment variables: GPG_SECRET_KEYS, GPG_OWNERTRUST, GPG_EXECUTABLE

set -eo pipefail

echo -n "$GPG_SECRET_KEYS" | base64 --decode --ignore-garbage | $GPG_EXECUTABLE --batch --import
echo -n "$GPG_OWNERTRUST" | base64 --decode --ignore-garbage | $GPG_EXECUTABLE --batch --import-ownertrust
mvn --batch-mode deploy -Prelease -DskipTests --settings .github/settings.xml
