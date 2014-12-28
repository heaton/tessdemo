image_namespace=heaton
name=tessdemo

install:
	docker build -t ${image_namespace}/${name} .
	docker tag ${image_namespace}/${name} ${image_namespace}/${name}:latest
pull:
	docker pull ubuntu:14.04
