FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build 

FROM nginx:alpine
COPY --from=node /app/dist/angular-springboot-client /usr/share/nginx/html
EXPOSE 80
CMD ["nginx" , "-g",  "daemon off;"]