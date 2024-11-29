package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocumentWriterAlarm is a Querydsl query type for DocumentWriterAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocumentWriterAlarm extends EntityPathBase<DocumentWriterAlarm> {

    private static final long serialVersionUID = -714312422L;

    public static final QDocumentWriterAlarm documentWriterAlarm = new QDocumentWriterAlarm("documentWriterAlarm");

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public final NumberPath<Long> wano = createNumber("wano", Long.class);

    public final StringPath writer = createString("writer");

    public QDocumentWriterAlarm(String variable) {
        super(DocumentWriterAlarm.class, forVariable(variable));
    }

    public QDocumentWriterAlarm(Path<? extends DocumentWriterAlarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentWriterAlarm(PathMetadata metadata) {
        super(DocumentWriterAlarm.class, metadata);
    }

}

