package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberAlarm is a Querydsl query type for MemberAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberAlarm extends EntityPathBase<MemberAlarm> {

    private static final long serialVersionUID = 919809646L;

    public static final QMemberAlarm memberAlarm = new QMemberAlarm("memberAlarm");

    public final NumberPath<Long> ino = createNumber("ino", Long.class);

    public final NumberPath<Long> mano = createNumber("mano", Long.class);

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    public QMemberAlarm(String variable) {
        super(MemberAlarm.class, forVariable(variable));
    }

    public QMemberAlarm(Path<? extends MemberAlarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberAlarm(PathMetadata metadata) {
        super(MemberAlarm.class, metadata);
    }

}

