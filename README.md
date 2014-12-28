# Tesseract Demo

## What is it?
A demo for Tesseract in Java with JNA (Tess4J).

This demo is attend to test <a href="http://tess4j.sourceforge.net/" target="_blank">Tess4J</a> on several OS like MacOS, Ubuntu and Windows.

Tesseract: an open source OCR engine. More details, please see its <a href="https://code.google.com/p/tesseract-ocr/" target="_blank">web site</a>.

Image filters: Check <a href="http://www.jhlabs.com/ip/filters/index.html" target="_blank">here</a> to see more information.

Thanks to all contributors of these open source projects.

## Getting Started
The recommended approach to setup your enviroment is using docker.

### With Docker
If you have already installed docker, skip to the next step. If not, checkout [docker](https://www.docker.com) to install.

#### Install
Build a docker image by running

    make pull
    make

Once this completes, you can check your install by running

    ./build
    ./test

### Without Docker
See dependencies in Dockerfile, then try to install them on your local machine.

[Install Tesseract](https://code.google.com/p/tesseract-ocr/wiki/Compiling)

Change the java version in pom.xml to fit your local machine.

Check your install by running

    mvn clean install
    java -cp "lib/jai-imageio.jar:lib/tess4j.jar:target/tessdemo/WEB-INF/lib/jna-4.1.0.jar:target/classes" me.heaton.ocr.TessExample

#### Windows:
Install <a href="http://www.microsoft.com/en-us/download/details.aspx?id=30679" target="_blank">Visual C++ Redistributable for VS2012</a> or <a href="http://www.microsoft.com/en-au/download/details.aspx?id=40784" target="_blank">Visual C++ Redistributable for VS2013</a>.
Install [EXE File](https://tesseract-ocr.googlecode.com/files/tesseract-ocr-setup-3.02.02.exe)

Maybe also need to install [Leptonica](http://www.leptonica.org/).
