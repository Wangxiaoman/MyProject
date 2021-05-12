package com.data.logp;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class SmilesLogP {
    private String drugName;
    private String smiles = StringUtils.EMPTY;
    private String logP;
    private int page;
    private int tableIndex;
    @Override
    public String toString() {
        return "insert into smiles_logp(drug_name,drugbank_smiles,logp,page,table_index) values('" + drugName + "', '" + smiles + "','" + logP
                + "'," + page + "," + tableIndex +");";
    }
}
