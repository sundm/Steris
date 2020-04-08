package com.steris.wechat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsHeadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewsHeadExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNTitleIsNull() {
            addCriterion("n_title is null");
            return (Criteria) this;
        }

        public Criteria andNTitleIsNotNull() {
            addCriterion("n_title is not null");
            return (Criteria) this;
        }

        public Criteria andNTitleEqualTo(String value) {
            addCriterion("n_title =", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleNotEqualTo(String value) {
            addCriterion("n_title <>", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleGreaterThan(String value) {
            addCriterion("n_title >", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleGreaterThanOrEqualTo(String value) {
            addCriterion("n_title >=", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleLessThan(String value) {
            addCriterion("n_title <", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleLessThanOrEqualTo(String value) {
            addCriterion("n_title <=", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleLike(String value) {
            addCriterion("n_title like", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleNotLike(String value) {
            addCriterion("n_title not like", value, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleIn(List<String> values) {
            addCriterion("n_title in", values, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleNotIn(List<String> values) {
            addCriterion("n_title not in", values, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleBetween(String value1, String value2) {
            addCriterion("n_title between", value1, value2, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNTitleNotBetween(String value1, String value2) {
            addCriterion("n_title not between", value1, value2, "nTitle");
            return (Criteria) this;
        }

        public Criteria andNContentIsNull() {
            addCriterion("n_content is null");
            return (Criteria) this;
        }

        public Criteria andNContentIsNotNull() {
            addCriterion("n_content is not null");
            return (Criteria) this;
        }

        public Criteria andNContentEqualTo(String value) {
            addCriterion("n_content =", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentNotEqualTo(String value) {
            addCriterion("n_content <>", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentGreaterThan(String value) {
            addCriterion("n_content >", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentGreaterThanOrEqualTo(String value) {
            addCriterion("n_content >=", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentLessThan(String value) {
            addCriterion("n_content <", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentLessThanOrEqualTo(String value) {
            addCriterion("n_content <=", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentLike(String value) {
            addCriterion("n_content like", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentNotLike(String value) {
            addCriterion("n_content not like", value, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentIn(List<String> values) {
            addCriterion("n_content in", values, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentNotIn(List<String> values) {
            addCriterion("n_content not in", values, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentBetween(String value1, String value2) {
            addCriterion("n_content between", value1, value2, "nContent");
            return (Criteria) this;
        }

        public Criteria andNContentNotBetween(String value1, String value2) {
            addCriterion("n_content not between", value1, value2, "nContent");
            return (Criteria) this;
        }

        public Criteria andNImgIsNull() {
            addCriterion("n_img is null");
            return (Criteria) this;
        }

        public Criteria andNImgIsNotNull() {
            addCriterion("n_img is not null");
            return (Criteria) this;
        }

        public Criteria andNImgEqualTo(String value) {
            addCriterion("n_img =", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgNotEqualTo(String value) {
            addCriterion("n_img <>", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgGreaterThan(String value) {
            addCriterion("n_img >", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgGreaterThanOrEqualTo(String value) {
            addCriterion("n_img >=", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgLessThan(String value) {
            addCriterion("n_img <", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgLessThanOrEqualTo(String value) {
            addCriterion("n_img <=", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgLike(String value) {
            addCriterion("n_img like", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgNotLike(String value) {
            addCriterion("n_img not like", value, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgIn(List<String> values) {
            addCriterion("n_img in", values, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgNotIn(List<String> values) {
            addCriterion("n_img not in", values, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgBetween(String value1, String value2) {
            addCriterion("n_img between", value1, value2, "nImg");
            return (Criteria) this;
        }

        public Criteria andNImgNotBetween(String value1, String value2) {
            addCriterion("n_img not between", value1, value2, "nImg");
            return (Criteria) this;
        }

        public Criteria andNSeqIsNull() {
            addCriterion("n_seq is null");
            return (Criteria) this;
        }

        public Criteria andNSeqIsNotNull() {
            addCriterion("n_seq is not null");
            return (Criteria) this;
        }

        public Criteria andNSeqEqualTo(Integer value) {
            addCriterion("n_seq =", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqNotEqualTo(Integer value) {
            addCriterion("n_seq <>", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqGreaterThan(Integer value) {
            addCriterion("n_seq >", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_seq >=", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqLessThan(Integer value) {
            addCriterion("n_seq <", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqLessThanOrEqualTo(Integer value) {
            addCriterion("n_seq <=", value, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqIn(List<Integer> values) {
            addCriterion("n_seq in", values, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqNotIn(List<Integer> values) {
            addCriterion("n_seq not in", values, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqBetween(Integer value1, Integer value2) {
            addCriterion("n_seq between", value1, value2, "nSeq");
            return (Criteria) this;
        }

        public Criteria andNSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("n_seq not between", value1, value2, "nSeq");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}