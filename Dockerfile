FROM hmctspublic.azurecr.io/base/java:11-distroless

COPY build/libs/ccpay-scheduled-jobs.jar /opt/app/

CMD ["ccpay-scheduled-jobs.jar"]
