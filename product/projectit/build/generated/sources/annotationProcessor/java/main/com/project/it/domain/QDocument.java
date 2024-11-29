package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocument is a Querydsl query type for Document
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocument extends EntityPathBase<Document> {

    private static final long serialVersionUID = -1568566812L;

    public static final QDocument document = new QDocument("document");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final DatePath<java.time.LocalDate> approvalDate = createDate("approvalDate", java.time.LocalDate.class);

    public final EnumPath<com.project.it.constant.DocumentStatus> approved = createEnum("approved", com.project.it.constant.DocumentStatus.class);

    public final StringPath approver = createString("approver");

    public final StringPath description = createString("description");

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public final ListPath<String, StringPath> hano = this.<String, StringPath>createList("hano", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final ListPath<String, StringPath> sano = this.<String, StringPath>createList("sano", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public final EnumPath<com.project.it.constant.DocumentVisibility> visibility = createEnum("visibility", com.project.it.constant.DocumentVisibility.class);

    public final StringPath writer = createString("writer");

    public QDocument(String variable) {
        super(Document.class, forVariable(variable));
    }

    public QDocument(Path<? extends Document> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocument(PathMetadata metadata) {
        super(Document.class, metadata);
    }

}

