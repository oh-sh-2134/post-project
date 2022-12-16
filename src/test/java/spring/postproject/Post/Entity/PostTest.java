package spring.postproject.Post.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Post.PostDto.PostInfoDto;
import spring.postproject.Post.PostDto.PostUpdateDto;

import static org.assertj.core.api.Assertions.*;

public class PostTest {

    private Post post;
    private String title = "타이틀 입니다.";
    private String content = "내용없음";
    private Member member = Member.builder()
                            .nickName("nickname")
                            .password("password")
                            .userId("id")
                            .build();

    @BeforeEach
    void setup(){
        post = createPost(title,content);
        post.setMember(member);
    }

    @DisplayName("Post 객체 생성이 빌더 패턴으로 생성한다.")
    @Test
    public void constructor(){
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getMember()).isEqualTo(member);
    }


    @DisplayName("타이틀이 변경 가능하다")
    @Test
    public void canChangeTitle(){
        String newTitle = "새로운 타이틀";
        PostUpdateDto postUpdateDto= PostUpdateDto.builder()
                .title(newTitle)
                .content(content).build();


        post.update(postUpdateDto);

        assertThat(post.getTitle()).isNotEqualTo(title);
        assertThat(post.getTitle()).isEqualTo(newTitle);
    }


    @DisplayName("컨텐츠가 변경 가능하다")
    @Test
    public void canChangeContent() {
        String newContent = "새로운 컨텐츠";
        PostUpdateDto postUpdateDto= PostUpdateDto.builder()
                .title(title)
                .content(newContent).build();


        post.update(postUpdateDto);

        assertThat(post.getContent()).isNotEqualTo(content);
        assertThat(post.getContent()).isEqualTo(newContent);
    }


    @DisplayName("타이틀이 비어있으면 에러가 난다.")
    @Test
    public void validationTitleBlank() {
        String invalidIndex = "";

        //타이틀 변경시 에러
        assertThatThrownBy(() -> post.update(PostUpdateDto.builder().title(invalidIndex).content("content").build()))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Post 생성시 에러
        assertThatThrownBy(() -> createPost(invalidIndex, "content"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("타이틀 길이가 50자리 이상이면 에러가 난다")
    @Test
    public void validationTitleLengthOver() {
        String invalidIndex = "lijeqroijewriojowerijowejrojweorjwejrowejorijweoirjweoiroweqqwesad";

        //타이틀 변경시 에러
        assertThatThrownBy(() -> post.update(PostUpdateDto.builder().title(invalidIndex).content("content").build()))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Post 생성시 에러
        assertThatThrownBy(() -> createPost(invalidIndex, "content"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("컨텐츠가 비어있으면 에러가 난다")
    @Test
    public void validationContentBlank() {
        String invalidIndex = "";

        //컨텐츠 변경시 에러
        assertThatThrownBy(() -> post.update(PostUpdateDto.builder().content(invalidIndex).title("title").build()))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Post 생성시 에러
        assertThatThrownBy(() -> createPost("title", invalidIndex))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("컨텐츠 길이가 500자리 이상이면 에러가 난다")
    @Test
    public void validationContentLengthOver() {
        String invalidIndex = "lijeqroijewriojowerijowejrojweorjweasdasdiourhliuojkvjhiuahtlkjwbnthjkasdkuahruloahkwen" +
                "rlkajsdhkuhvluikzcxkjnfkjasdnkarlahuidlhzskfzkljsdnrkljhsuzkdlrhikulwakjenreaklwejrnlkjrowej" +
                "alisejoiljeolihurqliweurhikwelirkuhqwluierhliqweuhrlikqwherilkuqwheilruqwhielriorijweoirjweoiroweqqwesad"+
                "lijeqroijewriojowerijowejrojweorjweasdasdiourhliuojkvjhiuahtlkjwbnthjkasdkuahruloahkwen"+
                "lijeqroijewriojowerijowejrojweorjweasdasdiourhliuojkvjhiuahtlkjwbnthjkasdkuahruloahkwen"+
                "lijeqroijewriojowerijowejrojweorjweasdasdiourhliuojkvjhiuahtlkjwbnthjkasdkuahruloahkwen";

        //타이틀 변경시 에러
        assertThatThrownBy(() -> post.update(PostUpdateDto.builder().content(invalidIndex).title("title").build()))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Post 생성시 에러
        assertThatThrownBy(() -> createPost(title, invalidIndex))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("Counting 메소드가 호출되면 count가 +1 된다")
    @Test
    public void CountingTest() {
        int count = post.getCount();
        assertThat(count +1).isEqualTo(post.counting().getCount());

    }

    private Post createPost(String title, String content) {

        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}