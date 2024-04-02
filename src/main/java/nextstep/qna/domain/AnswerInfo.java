package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerInfo extends  BaseEntity {
  private final NsUser writer;

  private final String contents;


  public AnswerInfo(NsUser writer, String contents) {
    this.writer = writer;
    this.contents = contents;
  }

  public boolean isNotOwner(NsUser writer) {
    return !this.writer.equals(writer);
  }

  public NsUser getWriter() {
    return writer;
  }
  @Override
  public String toString() {
    return "writer =" + writer + ", contents = " +  contents;
  }
}
