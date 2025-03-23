package com.gov.cmr.transparisation_module.model.DTO;

import java.math.BigDecimal;

public interface FichePortefeuilleSummary {
    String getAct();
    BigDecimal getValeurMarche();
    BigDecimal getValeurComptable();
}
