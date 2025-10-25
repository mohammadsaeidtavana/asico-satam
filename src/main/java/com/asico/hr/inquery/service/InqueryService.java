package com.asico.hr.inquery.service;

import com.asico.hr.inquery.domain.nationalcode.NationalCodeRequestApi;
import com.asico.hr.inquery.domain.nationalcode.NationalCodeResponseApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarRequestApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarResponseApi;

/**
 * @author m.tavana
 * @since 2025\
 */
public interface InqueryService {

    NationalCodeResponseApi nationalCodeCheck(NationalCodeRequestApi requestApi);

    ShahkarResponseApi shahkar(ShahkarRequestApi requestApi);


}
