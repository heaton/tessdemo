FROM ubuntu:14.04

RUN apt-get update && apt-get install -y \
      default-jdk \
      autoconf \
      automake \
      libtool \
      libpng12-dev \
      libjpeg-dev \
      libtiff4-dev \
      zlib1g-dev \
      libleptonica-dev \
      wget \
      g++ \
      make \
      maven

RUN cd ~ && wget https://tesseract-ocr.googlecode.com/files/tesseract-ocr-3.02.02.tar.gz && tar -xf tesseract-ocr-3.02.02.tar.gz
RUN cd ~/tesseract-ocr && ./autogen.sh && ./configure && make && make install && ldconfig
RUN cd ~ && wget https://tesseract-ocr.googlecode.com/files/tesseract-ocr-3.02.eng.tar.gz && tar xf tesseract-ocr-3.02.eng.tar.gz
RUN cp ~/tesseract-ocr/tessdata/eng.traineddata /usr/local/share/tessdata/

RUN mkdir -p /usr/local/app
WORKDIR /usr/local/app

CMD []
