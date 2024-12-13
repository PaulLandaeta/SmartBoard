package edu.upb.lp.core.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upb.lp.genericgame.R;
import edu.upb.lp.core.activities.AboutMoreUsActivity.Member;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private List<Member> memberList;

    public MemberAdapter(List<Member> memberList) {
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member_of_group, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.imageOfMember.setImageResource(member.getImageResId());
        holder.nameOfMember.setText(member.getName());
        holder.rolOfGame.setText(member.getRole());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView imageOfMember;
        TextView nameOfMember, rolOfGame;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOfMember = itemView.findViewById(R.id.image_of_member);
            nameOfMember = itemView.findViewById(R.id.name_of_member);
            rolOfGame = itemView.findViewById(R.id.rol_of_game);
        }
    }
}
