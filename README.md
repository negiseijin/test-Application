# test-Application
test

[![Android CI](https://github.com/negiseijin/test-Application/actions/workflows/android.yml/badge.svg?branch=develop)](https://github.com/negiseijin/test-Application/actions/workflows/android.yml)

https://zenn.dev/seita/articles/d1dba77043be8fd50eeb
https://github.com/fastlane/fastlane/issues/16618
https://egashira.dev/blog/get-latest-pr-via-github-cli

https://zenn.dev/tmknom/books/pragmatic-composite-action/viewer/release

https://qiita.com/kazuma_f/items/00d24c18965765019492

https://www.rockandnull.com/github-actions-android-version/amp/

```
name: Bump version code & change version name
on:
  push:
    branches:
      - 'bump/v*.*.*'
      
jobs:
  bump-version-and-open-pr:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v3
        
      - name: Extract existing version code
        run: |
          # Extract version number from branch name
          version_name=${GITHUB_REF#refs/heads/bump/v}

          # Get existing version code from build.gradle
          version_code=$(grep "versionCode" app/build.gradle | awk '{print $2}' | tr -d '\n')

          # Increment existing version code by 1
          version_code=$((version_code + 1))

          # Set environment variable for later use
          echo "VERSION_NAME=$version_name" >> $GITHUB_ENV
          echo "VERSION_CODE=$version_code" >> $GITHUB_ENV

      - name: Increase version code and change version name
        run: |
          # Update build.gradle with new version code and name
          echo "${{ env.VERSION_CODE }} - ${{ env.version_name }}"
          sed -i "s/versionCode [0-9]\+/versionCode ${{ env.VERSION_CODE }}/g" app/build.gradle
          sed -i "s/versionName \"[^\"]*\"/versionName \"${{ env.VERSION_NAME }}\"/g" app/build.gradle

      - name: Commit and push changes
        run: |
          git config user.email "github-actions@github.com"
          git config user.name "Github Actions"
          git commit -am "Bump version code and change version name"
          git push origin HEAD
          
      - name: Open pull request
        uses: peter-evans/create-pull-request@v5.0.0
        with:
          commit-message: "Bumping to ${{ env.VERSION_CODE }}"
          branch: "${{ github.ref }}"
          title: "${{ env.VERSION_NAME }} - (${{ env.VERSION_CODE }})"
          body: "${{ env.VERSION_NAME }} - (${{ env.VERSION_CODE }})"
          base: "master"

```

https://techblog.exawizards.com/entry/2021/03/23/115834
