ifneq (,)
This makefile requires GNU Make.
endif

# TODO
# create work-dir and copy there files
# use port as variable

NOTEBOOK_NAME:=notebook
CURRENT_DIR:=$(shell pwd)/workdir

# target: help - display this help.
.PHONY: help
help:
	@egrep '^# target' [Mm]akefile

# target: init-docker - run notebook on jupyter using current directory
.PHONY: init-docker
init-docker:
	docker run -d -P --name $(NOTEBOOK_NAME) -v $(CURRENT_DIR):/home/jovyan/work jupyter/pyspark-notebook
	sleep 5
	docker logs --tail 3 $(NOTEBOOK_NAME)

# target: stop-docker - will stop docker without cleaning it
.PHONY: stop-docker
stop-docker:
	docker stop $(NOTEBOOK_NAME)

# target: remove-docker - will remove docker and its volume
.PHONY: remove-docker
remove-docker:
	docker rm -vf $(NOTEBOOK_NAME)

# target: copy-csv - will copy CSV from fantastats
.PHONY: copy-csv
copy-csv:
	rm -f workdir/*.csv
	cp ../fantastats/*.csv workdir/

# target: recipes - link to recipes
.PHONY: recipes
recipes:
	https://jupyter-docker-stacks.readthedocs.io/en/latest/using/recipes.html

