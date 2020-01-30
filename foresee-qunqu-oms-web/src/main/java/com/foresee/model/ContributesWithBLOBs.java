package com.foresee.model;

public class ContributesWithBLOBs extends Contributes {
    private String contributeDesc;

    private String caseDesc;

    public String getContributeDesc() {
        return contributeDesc;
    }

    public void setContributeDesc(String contributeDesc) {
        this.contributeDesc = contributeDesc == null ? null : contributeDesc.trim();
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc == null ? null : caseDesc.trim();
    }
}