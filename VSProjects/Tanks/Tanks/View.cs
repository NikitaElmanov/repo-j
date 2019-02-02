using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace Tanks
{
    partial class View : UserControl
    {
        Model model;
        ControllerMainForm mf;

        public View(Model model, ControllerMainForm mf)
        {
			InitializeComponent();

            this.model = model;
            this.mf = mf;
        }

        private void Draw(PaintEventArgs e)
        {
            #region Drawing walls
            for (var x = 0; x < 6; x++)
            {
                for (var y = 0; y < 6; y++)
                {
                    e.Graphics.DrawImage(model.wall.Img, new Point(x * 64 + 32, y * 64 + 32));
                }
            }
            #endregion

            #region Drawing FiredTanks
            for (var i = 0; i < model.FireTanks.Count; i++)
                e.Graphics.DrawImage(model.FireTanks[i].CurrentImg, new Point(model.FireTanks[i].X, model.FireTanks[i].Y));
            #endregion

            #region single drawing booms 
            for (var i = 0; i < model.SingleBoom.Count; i++ )
            {
                e.Graphics.DrawImage(model.SingleBoom[i].CurrentImg, new Point(model.SingleBoom[i].X, model.SingleBoom[i].Y));
                model.SingleBoom.RemoveAt(i);
            }
            #endregion

            #region Drawing apples
            foreach (var a in model.Apples)
            {
                e.Graphics.DrawImage(a.Img, new Point(a.X, a.Y));
            }
            #endregion

            #region Drawing bullets
            for(var i = 0; i < model.Bullets.Count; i++) {
                e.Graphics.DrawImage(model.Bullets[i].CurrentImg, new Point(model.Bullets[i].X, model.Bullets[i].Y));
            }
            #endregion

            #region Drawing tanks
            for (var i = 0; i < model.Tanks.Count; i++ )
                e.Graphics.DrawImage(model.Tanks[i].CurrentImg, new Point(model.Tanks[i].X, model.Tanks[i].Y));

            #endregion 

            #region Drawing packMan
            e.Graphics.DrawImage(model.PackMan.CurrentImg, new Point(model.PackMan.X, model.PackMan.Y));
            #endregion

            if (model.gameStatus != GameStatus.playing)
            {
                return;
            }
            Thread.Sleep(model.SpeedGame);
            Invalidate();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            Draw(e);
        }
    }
}
