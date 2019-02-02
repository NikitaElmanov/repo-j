using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Media;
using System.Globalization;

namespace Tanks
{
    public partial class MenuForm : Form
    {
        public SoundPlayer sp;

        public MenuForm()
        {
            InitializeComponent();

            sp = new SoundPlayer(Properties.Resources.fonToMenu);
            sp.PlayLooping();
        }

        public MenuForm GetMenuFrom()
        {
            return this;
        }

        private void btn_openForm2_Click(object sender, EventArgs e)
        {
            string TanksStr = ammountTanks.Value.ToString();   //сделали так, потому что в программе FxCop выдавало ошибку насчет (int)ammountApples.Value и (int)ammountTanks.Value
            string applesStr = ammountApples.Value.ToString();

            ControllerMainForm mainForm = new ControllerMainForm(416,
                                                                 Convert.ToInt32(TanksStr, new CultureInfo("En", true)),
                                                                 Convert.ToInt32(applesStr, new CultureInfo("En", true)),
                                                                 40);
            mainForm.Show();

            sp.Stop();

            this.Hide();
        }

        private void MenuForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            sp.Stop();
            Application.Exit();
        }
    }
}
