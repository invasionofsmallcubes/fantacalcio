ifneq (,)
This makefile requires GNU Make.
endif

# TODO
# create work-dir and copy there files
# use port as variable

NOTEBOOK_NAME:=notebook
CURRENT_DIR:=$(shell pwd)/workdir

.PHONY: init-docker
init-docker:
	docker run -d -P --name $(NOTEBOOK_NAME) -v $(CURRENT_DIR):/home/jovyan/work jupyter/pyspark-notebook
	sleep 5
	docker logs --tail 3 $(NOTEBOOK_NAME)

.PHONY: stop-docker
stop-docker:
	docker stop $(NOTEBOOK_NAME)

.PHONY: remove-docker
remove-docker:
	docker rm -vf $(NOTEBOOK_NAME)

.PHONY: copy-csv
copy-csv:
	rm *.csv
	mv ../fantastats/*.csv .

.PHONY: recipes
recipes:
	https://jupyter-docker-stacks.readthedocs.io/en/latest/using/recipes.html

