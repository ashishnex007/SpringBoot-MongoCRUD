package net.engineeringdigest.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponse {
    public String quote;
    public String author;
    public String category;
}
