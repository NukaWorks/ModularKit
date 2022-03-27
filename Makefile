# ModularKit Makefile
#
.DEFAULT_GOAL := default
TOOLS_DIR := tools
REV := $(shell git rev-parse --short HEAD)

.PHONY : clean build build-nightly deploy deploy-nightly default

define clean_buildconfig 
	rm -rf .buildconfig-pom.xml
endef

clean:
	rm -rvf target/
	$(call clean_buildconfig)

build-nightly:
	python3 $(TOOLS_DIR)/prebuild.py $(REV) devel
	mvn -f ./.buildconfig-pom.xml install

deploy:
	python3 $(TOOLS_DIR)/prebuild.py $(REV) release
	mvn -f ./.buildconfig-pom.xml deploy

deploy-nightly:
	python3 $(TOOLS_DIR)/prebuild.py $(REV) devel
	mvn -f ./.buildconfig-pom.xml deploy

build:
	python3 $(TOOLS_DIR)/prebuild.py $(REV) release
	mvn -f ./.buildconfig-pom.xml install

default: clean build-nightly

