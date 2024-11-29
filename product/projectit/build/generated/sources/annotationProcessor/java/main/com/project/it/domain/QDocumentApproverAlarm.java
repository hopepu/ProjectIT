package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocumentApproverAlarm is a Querydsl query type for DocumentApproverAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocumentApproverAlarm extends EntityPathBase<DocumentApproverAlarm> {

    private static final long serialVersionUID = -1439406328L;

    public static final QDocumentApproverAlarm documentApproverAlarm = new QDocumentApproverAlarm("documentApproverAlarm");

    public final NumberPath<Long> aano = createNumber("aano", Long.class);

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    public QDocumentApproverAlarm(String variable) {
        super(DocumentApproverAlarm.class, forVariable(variable));
    }

    public QDocumentApproverAlarm(Path<? extends DocumentApproverAlarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentApproverAlarm(PathMetadata metadata) {
        super(DocumentApproverAlarm.class, metadata);
    }

}

