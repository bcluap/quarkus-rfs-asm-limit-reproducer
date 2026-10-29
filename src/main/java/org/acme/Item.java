package org.acme;

import java.math.BigDecimal;
import java.util.List;

public class Item {
    public boolean checked;
    public Boolean passed;
    public boolean pending;
    public BigDecimal score;
    public String comment;
    public List<String> tags;
    public String lastRun;
    public Boolean inReview;
}
