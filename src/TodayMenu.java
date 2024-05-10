import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TodayMenu {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JLabel menuLabel;
    private static Timer timer;
    private static final int MENU_CHANGE_INTERVAL = 50;
    private static boolean menuRotationEnabled = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createUI();
        });
    }

    private static void createUI() {
        frame = new JFrame("Today's Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new CardLayout());

        // Font 설정
        Font defaultFont = UIManager.getFont("Label.font");
        Font largerFont = defaultFont.deriveFont(defaultFont.getSize() + 4f);

        JPanel loginPanel = new JPanel(new GridLayout(4, 2));
        frame.add(loginPanel, "login");

        JLabel userLabel = new JLabel("아이디:");
        userLabel.setFont(largerFont);
        loginPanel.add(userLabel);

        JTextField idField = new JTextField();
        loginPanel.add(idField);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setFont(largerFont); // 폰트 적용
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        JLabel placeholder = new JLabel();
        loginPanel.add(placeholder);

        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(frame.getContentPane(), "menu");
                menuLabel.setText("오늘의 메뉴를 선택하세요");
            }
        });
        loginPanel.add(loginButton);

        JPanel menuPanel = new JPanel(new BorderLayout());
        frame.add(menuPanel, "menu");

        menuLabel = new JLabel("", SwingConstants.CENTER);
        menuLabel.setFont(largerFont);
        menuPanel.add(menuLabel, BorderLayout.CENTER);

        JButton recommendButton = new JButton("추천");
        recommendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMenuRotation();
            }
        });
        menuPanel.add(recommendButton, BorderLayout.SOUTH);

        cardLayout = (CardLayout) frame.getContentPane().getLayout();
        frame.setVisible(true);
    }

    private static void toggleMenuRotation() {
        if (menuRotationEnabled) {
            stopMenuRotation();
        } else {
            startMenuRotation();
        }
    }

    private static void startMenuRotation() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showRandomMenu();
            }
        };
        timer = new Timer(MENU_CHANGE_INTERVAL, taskPerformer);
        timer.start();
        menuRotationEnabled = true;
    }

    private static void showRandomMenu() {
        String[] menus = {
            "김밥", "떡볶이", "라면", "햄버거", "피자", "초밥", "우동", "우육면", "짜장면", "짬뽕",
            "볶음밥", "오므라이스", "비빔밥", "불고기", "갈비탕", "된장찌개", "김치찌개", "부대찌개", "마라탕", "탕수육",
            "갈비", "삼겹살", "치킨", "족발", "보쌈", "파스타", "스테이크", "돈까스", "소고기 스튜", "찜닭",
            "해물찜", "갈비찜", "차슈덮밥", "카레라이스", "타코", "후라이드 치킨", "마라샹궈", "해물파전", "순대국", "뼈해장국",
            "칼국수", "만두국", "떡국", "비빔냉면", "콩국수", "팥빙수", "호떡", "붕어빵", "떡볶이 튀김", "계란말이",
            "오뎅", "김치전", "생선구이", "새우튀김", "오징어순대", "순두부찌개", "김치볶음밥", "라볶이", "제육볶음", "고등어구이",
            "감자전", "치즈돈까스", "매운닭발", "삼계탕", "바게트", "훈제오리", "고추잡채", "닭갈비", "멸치볶음", "곱창구이",
            "초계국수", "보쌈죽", "백반", "족발죽", "우렁쌈밥", "닭발죽", "치킨죽", "고등어조림", "무국", "집밥",
            "치킨 무", "만두", "부침개", "족발피자", "김치찜", "낙지볶음", "쥐포", "물회", "돼지국밥", "추어탕",
            "장어구이", "쭈꾸미볶음", "콩비지찌개", "해물 덮밥", "잡채", "부대찌개 덮밥", "돼지불백", "잔치국수", "쌀국수", "꼼장어 볶음"
        };
        Random random = new Random();
        int index = random.nextInt(menus.length);
        String menu = menus[index];
        menuLabel.setText(menu);
    }

    private static void stopMenuRotation() {
        if (timer != null) {
            timer.stop();
            timer = null;
            menuRotationEnabled = false;
        }
    }
}
