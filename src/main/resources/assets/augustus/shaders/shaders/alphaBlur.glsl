#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;
uniform float sigma;

varying vec2 uv;

float gaussian(float x, float sigma) {
    float pi = 3.14159265359;
    return exp(-(x * x) / (2.0 * sigma * sigma)) / (sqrt(2.0 * pi) * sigma);
}

void main() {
    float radius = 1.75 * sigma;
    vec2 step = texelSize * direction;

    vec3 color = vec3(0.0);
    float totalAlpha = 0.0;

    for (float i = -radius; i <= radius; i += 1.0) {
        vec4 smpl = texture2D(texture, uv + step * i);
        float weight = gaussian(i, sigma);
        color += smpl.rgb * smpl.a * weight;
        totalAlpha += smpl.a * weight;
    }

    if (totalAlpha > 0.0) {
        color /= totalAlpha;
    }

    gl_FragColor = vec4(color, totalAlpha);
}

