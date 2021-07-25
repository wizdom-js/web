package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    // 스프링한테 데이터 소스 주입받기
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        // 쿼리 짜기
        String sql = "insert into member(name) values(?)";

        Connection conn = null; // db conncetion 가져오기
        PreparedStatement pstmt = null; // sql 넣기
        ResultSet rs = null; // 결과 받기

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS); // db inssert 해봐야 id 1번, 2번 얻을 수 있었는데 그떄 필요

            pstmt.setString(1, member.getName()); // 위의 ?랑 매칭. member.getname() 값 넣어준다.

            pstmt.executeUpdate(); // db에 쿼리 날아감
            rs = pstmt.getGeneratedKeys(); // 키 생성했는데 (id) 그거 반환해준다.

            // 값이 있으면
            if (rs.next()) {
                // 값 꺼내고 세팅
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            // 끝나고 나면 릴리즈. 리소스 바로 반환해야한다. 그렇지 않으면 db connection 계속 쌓이다가 난리난다.
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery(); // 조회이므로 executeQuery

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        } }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

    }
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        // 참고. 이 코드 칠 일 없음.
        // 스프링 프레임워크를 통해서 datasource를 쓸 때는
        // datasourceutils를 통해서 이 커넥션을 획득해야한다. 그래야 db 트랜젝션 걸릴 수 있는데
        // 그럼 db 커넥션 똑같이 유지해야한다. 똑같이 유지시켜주는 역할을 한다.
        // 그래서 스프링 프레임워크 쓸때는 이렇게 갖와야한다.
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } try {
        if (conn != null) {
            close(conn);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    private void close(Connection conn) throws SQLException {
        // 닫을  때에도 datasourceutils를 통해서 해야한다.
        DataSourceUtils.releaseConnection(conn, dataSource);
    } }