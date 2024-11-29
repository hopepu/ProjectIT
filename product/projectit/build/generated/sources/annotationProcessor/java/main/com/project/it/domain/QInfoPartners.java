package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInfoPartners is a Querydsl query type for InfoPartners
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInfoPartners extends EntityPathBase<InfoPartners> {

    private static final long serialVersionUID = -442553406L;

    public static final QInfoPartners infoPartners = new QInfoPartners("infoPartners");

    public final StringPath address = createString("address");

    public final StringPath bizType = createString("bizType");

    public final StringPath ceoName = createString("ceoName");

    public final NumberPath<Long> cno = createNumber("cno", Long.class);

    public final StringPath comName = createString("comName");

    public final StringPath coNum = createString("coNum");

    public final StringPath phone = createString("phone");

    public final StringPath site = createString("site");

    public QInfoPartners(String variable) {
        super(InfoPartners.class, forVariable(variable));
    }

    public QInfoPartners(Path<? extends InfoPartners> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInfoPartners(PathMetadata metadata) {
        super(InfoPartners.class, metadata);
    }

}

