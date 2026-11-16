#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;

uniform float sigma;
uniform float[200] sigmas;
uniform vec3 startColor;

varying vec2 uv;

#define step texelSize * direction
#define radius 1.75 * sigma


void main() {
    vec3 color = vec3(0);
    float totalAlpha = 0;
    int ii = 0;
    for (float i = -radius; i <= radius; i++) {
        vec4 smpl = texture2D(texture, uv + step * i);
        float alpha = smpl.a * sigmas[ii];
        color += smpl.rgb * alpha;
        totalAlpha += alpha;
        ii++;
    }

    color /= totalAlpha;
    gl_FragColor = vec4(color, totalAlpha);
}

