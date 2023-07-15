package com.spedire.Spedire.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

import static com.spedire.Spedire.utils.EmailConstants.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendEmailRequest {

    @JsonProperty(SENDER)
    private MailSender sender;
    @JsonProperty(TO)
    private Set<EmailRecipient> recipients;
    @JsonProperty(SUBJECT)
    private String subject;
    @JsonProperty(HTML_CONTENT_VALUE)
    private String content;

    private String token;
}