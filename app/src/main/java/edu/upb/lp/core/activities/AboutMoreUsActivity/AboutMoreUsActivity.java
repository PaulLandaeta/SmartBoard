package edu.upb.lp.core.activities.AboutMoreUsActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.upb.lp.genericgame.R;
import edu.upb.lp.core.adapter.MemberAdapter;

public class AboutMoreUsActivity extends AppCompatActivity {

    private TextView nameOfGameOnTitle, nameOfGame, year, numbersMembers;
    private ImageView imageGame;
    private RecyclerView memberRecyclerView;
    private MemberAdapter memberAdapter;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_more_us);

        nameOfGameOnTitle = findViewById(R.id.name_of_game_on_the_title);
        nameOfGame = findViewById(R.id.name_of_game);
        year = findViewById(R.id.year);
        imageGame = findViewById(R.id.image_game);
        numbersMembers = findViewById(R.id.numbers_members);
        memberRecyclerView = findViewById(R.id.member_recycler_view);

        memberList = new ArrayList<>();
        memberAdapter = new MemberAdapter(memberList);
        memberRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        memberRecyclerView.setAdapter(memberAdapter);
    }

    public void setGameTitle(String title) {
        nameOfGameOnTitle.setText(title);
    }

    public void setGameName(String gameName) {
        nameOfGame.setText(gameName);
    }

    public void setYear(String gameYear) {
        year.setText(gameYear);
    }

    public void setGameImage(int imageResId) {
        imageGame.setImageResource(imageResId);
    }

    public void addMember(int imageResId, String fullName, String role) {
        Member newMember = new Member(imageResId, fullName, role);
        memberList.add(newMember);
        memberAdapter.notifyItemInserted(memberList.size() - 1);
        updateMemberCount();
    }

    private void updateMemberCount() {
        numbersMembers.setText(String.format(Locale.ROOT, "(%d)", memberList.size()));
    }

}
