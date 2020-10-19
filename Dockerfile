FROM php:5.6-fpm

RUN apt-get update && apt-get install -y \
        git \
        libfreetype6-dev \
        libjpeg62-turbo-dev \
        libmcrypt-dev \
        libpng-dev \
        libssl-dev \
        libmemcached-dev \
        libz-dev \
        libmariadbclient18 \
        zlib1g-dev \
        libsqlite3-dev \
        zip \
        libxml2-dev \
        libcurl3-dev \
        libedit-dev \
        libpspell-dev \
        libldap2-dev \
        unixodbc-dev \
        libpq-dev

RUN ln -fs /usr/lib/x86_64-linux-gnu/libldap.so /usr/lib/

RUN echo "Installing PHP extensions" \
    && docker-php-ext-install iconv mcrypt gd pdo_mysql pdo_pgsql pcntl zip bcmath simplexml xmlrpc soap pspell ldap mbstring mysqli \
    && docker-php-ext-configure gd --with-freetype-dir=/usr/include/ --with-jpeg-dir=/usr/include/ \
    && docker-php-ext-enable iconv mcrypt gd pdo_mysql pdo_pgsql pcntl zip bcmath simplexml xmlrpc soap pspell ldap mbstring mysqli \
    && apt-get autoremove -y \
    && dpkg -la | awk '{print $2}' | grep '\-dev' | xargs apt-get remove -y \
    && apt-get clean all \
    && rm -rvf /var/lib/apt/lists/* \
    && rm -rvf /usr/share/doc /usr/share/man /usr/share/locale \
    #&& rm -fv /usr/local/etc/php-fpm.d/*.conf \
    && rm -rvf /usr/src/php

WORKDIR /var/www

RUN ["chmod", "+x", "/data-entrypoint.sh"]
COPY ./docker-entrypoint.sh /
#RUN chmod +x /docker-entrypoint.sh/
ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 9000
CMD ["php-fpm"]