FROM node:lts-alpine3.23 AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci --legacy-peer-deps

COPY . .

RUN npm run build -- --configuration production || npm run build

FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /app/dist /tmp/dist

RUN if [ -d /tmp/dist/*/browser ]; then \
    cp -r /tmp/dist/*/browser/* /usr/share/nginx/html/; \
    elif [ -d /tmp/dist/* ]; then \
    for dir in /tmp/dist/*/; do cp -r "$dir"* /usr/share/nginx/html/ && break; done; \
    else \
    cp -r /tmp/dist/* /usr/share/nginx/html/; \
    fi && \
    rm -rf /tmp/dist

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]