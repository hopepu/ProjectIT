package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumentReject is a Querydsl query type for DocumentReject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocumentReject extends EntityPathBase<DocumentReject> {

    private static final long serialVersionUID = 336328355L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocumentReject documentReject = new QDocumentReject("documentReject");

    public final QDocument document;

    public final NumberPath<Long> drno = createNumber("drno", Long.class);

    public final StringPath reason = createString("reason");

    public final StringPath rejector = createString("rejector");

    public QDocumentReject(String variable) {
        this(DocumentReject.class, forVariable(variable), INITS);
    }

    public QDocumentReject(Path<? extends DocumentReject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocumentReject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocumentReject(PathMetadata metadata, PathInits inits) {
        this(DocumentReject.class, metadata, inits);
    }

    public QDocumentReject(Class<? extends DocumentReject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.document = inits.isInitialized("document") ? new QDocument(forProperty("document")) : null;
    }

}

