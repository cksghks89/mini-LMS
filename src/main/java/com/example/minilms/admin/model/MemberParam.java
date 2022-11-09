package com.example.minilms.admin.model;

import lombok.Data;

@Data
public class MemberParam {
    String userId;

    String searchType;
    String searchValue;

    long pageIndex;
    long pageSize;

    public long getPageStart() {
        init();
        return (this.pageIndex - 1) * pageSize;
    }

    public long getPageEnd(){
        init();
        return pageSize;
    }

    public void init(){
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        if(pageSize < 10){
            pageSize = 10;
        }
    }

    public String getQueryString() {
        init();

        StringBuilder sb = new StringBuilder();

        if(searchType != null && searchType.length() > 0){
            sb.append(String.format("searchType=%s", searchType));
        }

        if(searchValue != null && searchValue.length() > 0){
            if(sb.length() > 0){
                sb.append("&");
            }
            sb.append(String.format("searchValue=%s", searchValue));
        }
        return sb.toString();
    }
}
