#!/bin/bash

function check_blade {
	if [ ! -e ~/jpm/bin/blade ]
	then
		echo "Blade CLI is not available. To install Blade CLI, execute the following command:"
		echo ""

		echo "curl -L https://raw.githubusercontent.com/liferay/liferay-blade-cli/master/cli/installers/local | sh"

		exit 1
	fi
}

function refresh_able_remote_app {

	#
	# TODO The command "yarn build" breaks if checked out from Git
	#

	rm -fr sample-lxc-workspace/lxc/extensions/able-remote-app

	pushd sample-lxc-workspace/lxc/extensions > /dev/null

	../../../../tools/create_remote_app.sh able-remote-app react

	pushd able-remote-app > /dev/null

	yarn build

	popd > /dev/null

	popd > /dev/null
}

function refresh_baker_webhook {
	cp sample-lxc-sm-workspace/gradlew sample-lxc-workspace/lxc/extensions/baker-webhook

	cp -R sample-lxc-sm-workspace/gradle sample-lxc-workspace/lxc/extensions/baker-webhook
}

function refresh_sample_lxc_sm_workspace {
	check_blade

	rm -fr sample-lxc-sm-workspace

	mkdir sample-lxc-sm-workspace

	cd sample-lxc-sm-workspace

	~/jpm/bin/blade init --liferay-version dxp-7.4-u20

	touch modules/.gitkeep
	touch themes/.gitkeep

	cd ..

	#
	# TODO Liferay Workspace needs to ignore the lxc directory
	#

	cp -R sample-lxc-workspace/lxc sample-lxc-sm-workspace
}

function refresh_sample_lxc_workspace {
	refresh_able_remote_app
	refresh_baker_webhook
}

function main {
	#refresh_sample_lxc_workspace

	refresh_sample_lxc_sm_workspace
}

main "${@}"