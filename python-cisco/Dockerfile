FROM ubuntu

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=America/Edmonton

RUN apt update && apt dist-upgrade -y && apt install -y neovim fish python3 python3-pip curl lsb-release tzdata
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

RUN mkdir /lab && mkdir /root/.config
WORKDIR /root/.config

RUN sh -c "$(curl -fsSL https://raw.githubusercontent.com/rz3n/terminal/master/install.sh)"

WORKDIR /lab

## cleanup
RUN apt purge -y bzip2
RUN apt autoremove -y && apt clean -y && apt autoclean -y
RUN rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/*

# change terminal to fish
RUN sed -i 's/bash/fish/g' /etc/passwd

