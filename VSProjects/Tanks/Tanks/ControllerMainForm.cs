using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.Media;

[assembly: CLSCompliant(true)]

namespace Tanks
{
    public delegate void DIncreaseScore();
    public delegate void DChangeGameStatus();

    public partial class ControllerMainForm : Form
    {
        private event EventHandler IncScore;
        private DIncreaseScore dIncreaseScoreRef;

        private event DChangeGameStatus changeStatus;
        private DChangeGameStatus dChangeGameStatusRef;

        View view;

        Model model;

        MenuForm menuForm;

        Thread modelPlay;

        bool isSoundPlay;

        SoundPlayer spController;

        public ControllerMainForm() : this(416) { }

        public ControllerMainForm(int sizeFiled) : this(sizeFiled, 5) { }

        public ControllerMainForm(int sizeField, int ammountTanks) : this( sizeField, ammountTanks, 5) { }

        public ControllerMainForm(int sizeField, int ammountTanks, int ammoutApples) : this(sizeField, ammountTanks, ammoutApples, 40) { }

        public ControllerMainForm(int sizeField, int ammountTanks, int ammoutApples, int speedGame)
        {
            InitializeComponent();

            spController = new SoundPlayer(Properties.Resources.playMusic);

            dIncreaseScoreRef = new DIncreaseScore(ChangeScore);
            IncScore += Controller_MainForm_IncScore;

            dChangeGameStatusRef = new DChangeGameStatus(ChangeGameStatus);
            changeStatus += Controller_MainForm_changeStatus;

            model = new Model(sizeField, ammountTanks, ammoutApples, speedGame, IncScore, changeStatus);

            view = new View(model, this);
            this.Controls.Add(view);
            view.Location = new Point(0, 20);

            isSoundPlay = true;

            menuForm = new MenuForm();
        }

        void Controller_MainForm_changeStatus()
        {
            if (Game_statusStrip.InvokeRequired)
            {
                Invoke(dChangeGameStatusRef);
            }
            else
            {
                ChangeGameStatus();
            }
        }

        void Controller_MainForm_IncScore(object sender, EventArgs e)
        {
            if(lbl_Score.InvokeRequired) 
            {
                Invoke(dIncreaseScoreRef);
            }
            else
            {
                ChangeScore();
            }
        }

        void ChangeScore() {
            int lastNum = Int32.Parse(lbl_Score.Text);
            lbl_Score.Text = (lastNum + 1).ToString();
        }

        private void btnStart_Stop_Click(object sender, EventArgs e)
        {
            if (model.gameStatus == GameStatus.playing)
            {
                MainMenuStrip.Show();
                soundToolStripMenuItem.Enabled = true;
                howToPlayToolStripMenuItem.Enabled = true;
                modelPlay.Abort();
                model.gameStatus = GameStatus.stoping;
            }
            else
            {
                MainMenuStrip.Hide();
                soundToolStripMenuItem.Enabled = false;
                howToPlayToolStripMenuItem.Enabled = false;
                StartStop_pcbx.Focus();
                modelPlay = new Thread(model.Play);
                modelPlay.Start();
                model.gameStatus = GameStatus.playing;
                view.Invalidate(); // this fun give start inertion for game
            }

            ChangeGameStatus();
        }

        private void Controller_MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if(modelPlay != null) {
                model.gameStatus = GameStatus.stoping;
                modelPlay.Abort();
            }

            DialogResult dr = MessageBox.Show("Do you really want to close the game sesstion?",
                                              "Tanks",
                                              MessageBoxButtons.YesNo,
                                              MessageBoxIcon.Asterisk,
                                              MessageBoxDefaultButton.Button1,
                                              MessageBoxOptions.DefaultDesktopOnly);

            if(dr == DialogResult.Yes) {
                e.Cancel = false;
                menuForm.GetMenuFrom().Show();
                menuForm.sp.PlayLooping();
            }
            else
            {
                e.Cancel = true;
            }
        }

        public void ChangeGameStatus()
        {
            GameStatus_lbl.Text = model.gameStatus.ToString();
            if (isSoundPlay){
                if (model.gameStatus == GameStatus.playing)
                {
                    spController.PlayLooping();
                }
                else
                {
                    spController.Stop();
                }
            }
        }

        private void StartStop_pcbx_PreviewKeyDown(object sender, PreviewKeyDownEventArgs e)
        {
            switch ( (char)e.KeyData )
            {
                //Up = W
                case 'W':
                    {
                        model.PackMan.NextDirectX = 0;
                        model.PackMan.NextDirectY = -1;
                        break;
                    }
                //Down = S
                case 'S':
                    {
                        model.PackMan.NextDirectX = 0;
                        model.PackMan.NextDirectY = 1;
                        break;
                    }
                //Right = D
                case 'D':
                    {
                        model.PackMan.NextDirectX = 1;
                        model.PackMan.NextDirectY = 0;
                        break;
                    }
                //Left = A
                case 'A':
                    {
                        model.PackMan.NextDirectX = -1;
                        model.PackMan.NextDirectY = 0;
                        break;
                    }
                case ' ':
                    {
                        model.Bullets.Add(new Bullet(model.PackMan.X, model.PackMan.Y, model.PackMan.Direct_x, model.PackMan.Direct_y));
                        break;
                    }
                default:
                    {
                        StartStop_pcbx.Focus();
                        break;
                    }
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void NewGameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            howToPlayToolStripMenuItem.Enabled = true;
            model.NewGame();
            ChangeGameStatus();
            lbl_Score.Text = "0";
            view.Refresh();
        }

        private void howToPlayToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Game 'Takns' v1.0\nControlling: 'A', 'D','W','S', Fire - 'SPACE'",
                            "Tanks Help",
                            MessageBoxButtons.OK,
                            MessageBoxIcon.Asterisk,
                            MessageBoxDefaultButton.Button1,
                            MessageBoxOptions.DefaultDesktopOnly);
        }

        private void soundToolStripMenuItem_Click(object sender, EventArgs e)
        {
            isSoundPlay = !isSoundPlay;
            if(isSoundPlay) 
            {
                soundToolStripMenuItem.Text = "Sound Off";
            }
            else
            {
                soundToolStripMenuItem.Text = "Sound On";
            }
        }
    }
}
