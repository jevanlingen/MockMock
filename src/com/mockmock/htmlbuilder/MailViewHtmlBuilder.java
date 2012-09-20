package com.mockmock.htmlbuilder;

import com.mockmock.mail.MockMail;
import org.apache.commons.lang3.StringEscapeUtils;

public class MailViewHtmlBuilder implements HtmlBuilder
{
    private MockMail mockMail;

    public void setMockMail(MockMail mockMail)
    {
        this.mockMail = mockMail;
    }

    public String build()
    {
        MailViewHeadersHtmlBuilder headersBuilder = new MailViewHeadersHtmlBuilder();
        headersBuilder.setMockMail(mockMail);

        AddressesHtmlBuilder addressesHtmlBuilder = new AddressesHtmlBuilder();
        addressesHtmlBuilder.setMockMail(mockMail);

        String subjectOutput;
        if(mockMail.getSubject() == null)
        {
            subjectOutput = "<em>No subject given</em>";
        }
        else
        {
            subjectOutput = StringEscapeUtils.escapeHtml4(mockMail.getSubject());
        }

        String output = "<div class=\"container\">\n";

        output +=
                "<h2>" + subjectOutput + "</h2>\n" +
                "  <div class=\"row\">\n";

        output +=
                "    <div class=\"span10\">\n" +
                "       <h3>Addresses</h3>\n" +
                "       " + addressesHtmlBuilder.build() +
                "    </div>\n";

        output +=
                "    <div class=\"span10\">\n" +
                "       <h3>Mail headers</h3>\n" +
                "       " + headersBuilder.build() +
                "    </div>\n";

        if(mockMail.getBody() != null)
        {
            output +=
                    "    <div class=\"span10\">\n" +
                    "       <h3>Plain text body</h3>\n" +
                    "       <p class=\"well\">" + StringEscapeUtils.escapeHtml4(mockMail.getBody()).replaceAll("\n", "<br />") + "</p>\n" +
                    "    </div>\n";
        }

        if(mockMail.getBodyHtml() != null)
        {
            output +=
                    "    <div class=\"span10\">\n" +
                    "       <h3>HTML body</h3>\n" +
                    "       <p class=\"well\">" + StringEscapeUtils.escapeHtml4(mockMail.getBodyHtml()).replaceAll("\n", "<br />") + "</p>\n" +
                    "    </div>\n";
        }

        output +=
                "  </div>\n";

        output +=
                "</div>\n";

        return output;
    }
}