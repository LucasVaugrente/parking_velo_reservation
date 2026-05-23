FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci --legacy-peer-deps

COPY . .

# Build l'application
RUN npm run build -- --configuration production || npm run build

FROM nginx:alpine

# Vérifier la structure du dossier dist et copier correctement
# Angular crée généralement dist/nom-projet/browser ou dist/nom-projet
RUN rm -rf /usr/share/nginx/html/*

# Copier les fichiers buildés - on copie tout le contenu de dist
COPY --from=build /app/dist /tmp/dist

# Script pour trouver et copier les bons fichiers
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