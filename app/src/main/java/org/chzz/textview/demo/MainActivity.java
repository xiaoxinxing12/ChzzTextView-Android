package org.chzz.textview.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.chzz.textview.CHZZTextView;
import org.chzz.textview.CHZZTextViewType;


public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    String[] sentences = new String[]{"What is design?", "Design", "Design is not just", "what it looks like", "and feels like.", "Design", "is how it works.", "- Steve Jobs", "Older people", "sit down and ask,", "'What is it?'", "but the boy asks,", "'What can I do with it?'.", "- Steve Jobs", "Swift", "Objective-C", "iPhone", "iPad", "Mac Mini", "MacBook Pro", "Mac Pro", "爱老婆", "老婆和女儿"};
    private int mCounter = 10;
    private TextSwitcher textSwitcher;
    private CHZZTextView mTextView;

    private SeekBar    seekBar;
    private RadioGroup radioGroup;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSwitcher = (TextSwitcher) findViewById(R.id.text);
        textSwitcher.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        mTextView = (CHZZTextView) findViewById(R.id.text2);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8 + progress);
                mTextView.reset(mTextView.getText());
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(10);

        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.scale:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.SCALE);
                        break;
                    case R.id.evaporate:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.EVAPORATE);
                        break;
                    case R.id.fall:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.FALL);
                        break;
                    case R.id.pixelate:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.PIXELATE);
                        break;
                    case R.id.sparkle:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.SPARKLE);
                        break;
                    case R.id.anvil:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.ANVIL);
                        break;
                    case R.id.line:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.LINE);
                        break;
                    case R.id.typer:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.TYPER);
                        break;
                    case R.id.rainbow:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.RAINBOW);
                        break;
                }

                onClick(radioGroup.findViewById(checkedId));
            }
        });
//
//        mTextView.setTextColor(Color.BLACK);
//        mTextView.setBackgroundColor(Color.WHITE);
//        mTextView.setAnimateType(CHZZTextViewType.SCALE);
//        onClick(findViewById(R.id.scale));
    }

    public void onClick(View v) {
        updateCounter();
    }

    private void updateCounter() {
        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        textSwitcher.setText(sentences[mCounter]);
        mTextView.animateText(sentences[mCounter]);
    }

    @Override public View makeView() {

        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        return t;
    }
}
