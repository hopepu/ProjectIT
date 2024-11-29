package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRooms is a Querydsl query type for ChatRooms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRooms extends EntityPathBase<ChatRooms> {

    private static final long serialVersionUID = -716519721L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRooms chatRooms = new QChatRooms("chatRooms");

    public final ListPath<ChatUsers, QChatUsers> chatUsers = this.<ChatUsers, QChatUsers>createList("chatUsers", ChatUsers.class, QChatUsers.class, PathInits.DIRECT2);

    public final NumberPath<Long> crno = createNumber("crno", Long.class);

    public final StringPath name = createString("name");

    public final QProject project;

    public QChatRooms(String variable) {
        this(ChatRooms.class, forVariable(variable), INITS);
    }

    public QChatRooms(Path<? extends ChatRooms> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRooms(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRooms(PathMetadata metadata, PathInits inits) {
        this(ChatRooms.class, metadata, inits);
    }

    public QChatRooms(Class<? extends ChatRooms> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

