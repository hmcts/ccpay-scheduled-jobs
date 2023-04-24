package uk.gov.hmcts.payment.processors;

public class JobProcessorFactory {

    public JobProcessor getJobType(String jobType) {
        if (jobType == null) {
            return null;
        }
        if(jobType.equalsIgnoreCase("status-update")){
            return new StatusUpdateProcessor();

        }
        if(jobType.equalsIgnoreCase("dead-letter-queue-process")){
            return new DeadLetterQueueProcessor();

        }
        if(jobType.equalsIgnoreCase("bar-csv-report")){
            return new BarCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("card-csv-report")){
            return new CardCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("pba-csv-report")){
            return new PbaCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("pba-finrem-weekly-csv-report")){
            return new PbaFinremWeeklyCsvReportProcessor();

        }

        if(jobType.equalsIgnoreCase("refund-notifications-job")){
            return new RefundNotificationUpdateProcessor();
         }

        if(jobType.equalsIgnoreCase("unprocessed-payment-update")){
            return new UnprocessedPaymentUpdateProcessor();
        }

        if(jobType.equalsIgnoreCase("duplicate-payment-process")){
            return new DuplcatePaymentProcessor();
        }

        return null;
    }
}
