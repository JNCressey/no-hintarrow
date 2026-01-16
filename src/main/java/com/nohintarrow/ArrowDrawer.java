/*
BSD 2-Clause License

Copyright (c) 2025, LlemonDuck
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.nohintarrow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import static net.runelite.api.Perspective.LOCAL_HALF_TILE_SIZE;
import static net.runelite.api.Perspective.LOCAL_TILE_SIZE;

import net.runelite.api.Player;
import net.runelite.api.WorldView;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;


public class ArrowDrawer //based on the boat arrow from LlemonDuck/sailing plugin
{


    // arrow facing right
    // spans about 2 full tiles
    private static final float[] ARROW_X = new float[]{
            0,
            -3 * ((float) LOCAL_TILE_SIZE / 4),
            -3 * ((float) LOCAL_TILE_SIZE / 4),
            0,
            0,
            +3 * ((float) (5 * LOCAL_TILE_SIZE) / 16),
            0,
    };
    private static final float[] ARROW_Y = new float[]{
            +((float) LOCAL_TILE_SIZE / 8), // box top right
            +((float) LOCAL_TILE_SIZE / 8), // box bottom right
            -((float) LOCAL_TILE_SIZE / 8), // box bottom left
            -((float) LOCAL_TILE_SIZE / 8), // box top left
            -((float) (5 * LOCAL_TILE_SIZE) / 16), // head left
            0, // head top
            +((float) (5 * LOCAL_TILE_SIZE) / 16), // head right
    };

    public static int jauBetween(LocalPoint p1, LocalPoint p2)
    {
        int dx = p2.getX() - p1.getX();
        int dy = p2.getY() - p1.getY();
        double radialStep = 2048. / (Math.PI * 2.);
        return ((int) Math.round(Math.atan2(dy, -dx) * radialStep)) & 2047;
    }

    public static float[] translate(float[] src, int offset)
    {
        float[] translated = new float[src.length];
        for (int i = 0; i < src.length; i++)
        {
            translated[i] = src[i] + offset;
        }
        return translated;
    }

    public static void renderArrowTowardPoint(Graphics2D g, Client client, WorldPoint destination, Color color, int clearRadius)
    {

        WorldView tlwv = client.getTopLevelWorldView();
        int baseX = tlwv.getBaseX();
        int baseY = tlwv.getBaseY();

        LocalPoint targetLp = LocalPoint.fromScene(destination.getX() - baseX, destination.getY() - baseY, tlwv); // maybe outside the scene
        LocalPoint playerLp = client.getLocalPlayer().getLocalLocation();

        float[] arrowX = ArrowDrawer.translate(ARROW_X, clearRadius * LOCAL_HALF_TILE_SIZE); // push the arrow outside the boat
        float[] arrowY = ARROW_Y;

        int[] outXs = new int[arrowX.length];
        int[] outYs = new int[arrowY.length];
        Perspective.modelToCanvas(
                client,
                client.getTopLevelWorldView(),
                arrowX.length,
                playerLp.getX(),
                playerLp.getY(),
                getPlayerZ3dCentre(client),//0,
                ArrowDrawer.jauBetween(targetLp, playerLp),
                arrowX,
                arrowY,
                new float[arrowX.length],
                outXs,
                outYs
        );

        g.setColor(color);
        g.fill(new Polygon(outXs, outYs, outXs.length));
    }
    /**
      * @return the 3d z coordinate of the ground under the player for the Perspective.modelToCanvas call
     */
    static int getPlayerZ3dCentre(Client client){
        Player localPlayer = client.getLocalPlayer();
        LocalPoint lp = localPlayer.getLocalLocation();
        int sceneX = lp.getSceneX();
        int sceneY = lp.getSceneY();
        WorldView wv = client.getTopLevelWorldView();
        int z3dCentre = wv.getTileHeights()[wv.getPlane()][sceneX][sceneY];
        return z3dCentre;
    }

}
